//package springjpa;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import springjpa.repository.BidRepository;
//import springjpa.repository181.TagActiveTagRepository;
//
//
///**
// * Created by wenxiangzhou214164 on 2017/9/29
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ApplicationTest {
//
//    @Autowired
//    private BidRepository bidRepository;
//    @Autowired
//    private TagActiveTagRepository tagRepository;
//
//    @Test
//    public void getBid() {
//        System.out.println(bidRepository.findMinId());
//    }
//
//    @Test
//    public void getTag() {
//        System.out.println(tagRepository.findOne(100008).getTagName());
//    }
//
//}