package xyz.bugcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.ProductImage;
import xyz.bugcoder.service.ProductImageService;
import xyz.bugcoder.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.controller
 * @Description:
 * @Date: 2019-09-21 16:17
 * @Author: Wyj
 */
@RestController
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;

    // 根据前端传过来的type来获取产品图片
    @GetMapping("/products/{pid}/productImages")
    public List<ProductImage> list(@RequestParam("type") String type,
                                   @PathVariable("pid") int pid) throws Exception {
//        Product product = productService.get(pid);

        if(ProductImageService.type_single.equals(type)) {
            List<ProductImage> singles =  productImageService.listSingleImages(pid);
            return singles;
        }
        else if(ProductImageService.type_detail.equals(type)) {
            List<ProductImage> details =  productImageService.listDetailImages(pid);
            return details;
        }
        else {
            return new ArrayList<>();
        }
    }

    // 添加产品图片
    @PostMapping("/productImages")
    public Object add(@RequestParam("type")String type,
                    @RequestParam("pid") int pid,
                    MultipartFile image, HttpServletRequest request) throws IOException {

        ProductImage pi = new ProductImage();
        Product p = productService.get(pid);
        pi.setType(type);
        pi.setPid(pid);
        pi.setProduct(p);
        productImageService.add(pi);

        // 根据传过来的类型放入文件夹
        String folder = "images/";
        if (ProductImageService.type_single.equals(type)){

            folder += "productSingle";
        } else {

            folder += "productDetail";
        }

        // 获取路径
        File imageFolder = new File(request.getServletContext().getRealPath(folder));
        File imageFile = new File(imageFolder, pi.getId() + ".jpg");
        if (!imageFile.getParentFile().exists())
            imageFile.getParentFile().mkdirs();

        System.out.println(image.getName());
        if (image == null){

            System.out.println("图片空的");
        }
        image.transferTo(imageFile);
        System.out.println("保存图片");

        return  pi;
    }

}
