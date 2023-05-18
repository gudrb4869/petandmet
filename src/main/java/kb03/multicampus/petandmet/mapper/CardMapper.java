package kb03.multicampus.petandmet.mapper;

import java.util.List;



import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kb03.multicampus.petandmet.dto.CardDto;

@Mapper
public interface CardMapper {
	@Select("Select * FROM card ORDER BY no DESC")
	List<CardDto> findAll();
	
}
