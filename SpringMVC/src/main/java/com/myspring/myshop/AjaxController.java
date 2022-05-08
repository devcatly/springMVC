package com.myspring.myshop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.domain.CategoryVO;
import com.myspring.domain.ProductVO;
import com.myspring.service.AdminService;
import com.sun.media.jfxmedia.logging.Logger;
/*pom.xml에 아래 라이브러리 등록해야 함
 * ===================================
 * <!-- json lib -->
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.9.8</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml -->
		<dependency>
			<groupId>com.fasterxml.jackson.dataformat</groupId>
			<artifactId>jackson-dataformat-xml</artifactId>
			<version>2.9.8</version>
		</dependency>
 * ===================================
 * */
//Ajax를 이용해서 상품관리를 하는 컨트롤러
@RestController
public class AjaxController {
	
	@Inject
	private AdminService adminService;
	//default가 xml형태로 응답한다. 만약 json형태로 응답하고 싶다면
	//produces = "application/json"을 기술해주자
	@GetMapping(value="/cateAll",produces = "application/json")
	public List<CategoryVO> categoryAll(){
		List<CategoryVO> cateAll=this.adminService.getCategoryList();
		return cateAll;
	}

	//@GetMapping(value="/prodAll", produces="text/xml")
	@GetMapping(value="/prodAll", produces="application/json")
	public List<ProductVO> productAll(){
		List<ProductVO> prodAll=this.adminService.productList();
		return prodAll;
	}
	
	@GetMapping(value="/prodInfo", produces = "application/json")
	public List<ProductVO> productsByCategory(@RequestParam(defaultValue="0") int cg_num){
		//logger.info("cg_num={}",cg_num)
		List<ProductVO> products =this.adminService.getProducts(cg_num);
		return products;
	}
	

}




