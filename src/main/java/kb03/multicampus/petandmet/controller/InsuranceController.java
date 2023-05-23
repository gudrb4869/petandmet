package kb03.multicampus.petandmet.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kb03.multicampus.petandmet.dto.BreedInsuranceDto;
import kb03.multicampus.petandmet.dto.InsuranceDto;
import kb03.multicampus.petandmet.dto.PetDto;
import kb03.multicampus.petandmet.dto.ProductDto;
import kb03.multicampus.petandmet.dto.ProductRecommendRequestData;
import kb03.multicampus.petandmet.dto.UserDto;
import kb03.multicampus.petandmet.mapper.InsuranceMapper;
import kb03.multicampus.petandmet.service.InsuranceService;
import kb03.multicampus.petandmet.service.PetService;
import kb03.multicampus.petandmet.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/insurances")
@RequiredArgsConstructor
@Slf4j
public class InsuranceController {
	
	private final InsuranceMapper mapper;
	private final InsuranceService service;
	private final PetService petService;
	
	//전체 목록보기 주석처리 
//	@GetMapping("/insurances")
//	public String insurance(Model model) {
//		//db에서 보험 리스트 가져오기		
//        List<InsuranceDto> ins = mapper.findAll();
//
//        for (InsuranceDto i : ins) {
//			int max = (int)(i.getMax_age()/12);
//			i.setMax_age(max);
//			
//			int rate = (int)(i.getCoverage_ratio()*100);
//			i.setCoverage_ratio(rate);
//			
//			int year = (int)(i.getPeriod()/12);
//			i.setPeriod(year);
//		}
//		model.addAttribute("ins", ins);
//		
//		return "insurances";
//	}
	
//	@GetMapping("/insurances")
//	public String insurance(Model model) {
//		//db에서 보험 리스트 가져오기		
//        List<Map<String, Object>> li = mapper.getBreedInsurance();
//        model.addAttribute("li", li);
//		
//		return "insurances";
//	}
	

	//petdto에서 pet breed를 가져오기 
	@GetMapping
    public String insurance(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object petinfo = session.getAttribute("petinfo"); //펫 품종 불러오기 

		List<PetDto> pd = (List<PetDto>) petinfo;
        
		for (int i = 0; i < pd.size(); i++) {
			String breed = pd.get(i).getBreed().toString();
	        List<Map<String, Object>> petins = mapper.getBreedInsurance(breed);
	        model.addAttribute("petins", petins);
		}
               
        return "insurances";     
    }
	
	
	@ResponseBody
	@PostMapping
	public Map<String, Object> getBreedInsurance(String breed) {
		Map<String, Object> map = new HashMap<>();
		log.info("RequestBody: {}", breed);
		List<Map<String, Object>> li = service.getBreedInsurance(breed);
		
		map.put("li", li);
		
		log.info("HashMap: {}", map);
		return map;
	}
	
}
