package org.graduation.freshmanwelcome;

import org.graduation.freshmanwelcome.entity.College;
import org.graduation.freshmanwelcome.mapper.CollegeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreshmanWelcomeApplicationTests {

    @Autowired
    private CollegeMapper collegeMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSelect(){
        System.out.println(("----- selectAll method test ------"));
        List<College> collegeList = collegeMapper.findAll();
        for (College college:collegeList){
            System.out.println(college);
        }
    }

}
