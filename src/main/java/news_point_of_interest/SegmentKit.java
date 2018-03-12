package news_point_of_interest;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;

import java.util.List;

/**
 * Created by yonghongli on 2017/3/16.
 */
public class SegmentKit {
	/**
	 * 对句子进行切分
	 * @param sentence
	 * @return
	 */
	public static  List< Term>   segmentSentence(String sentence)
	{
	//	List<String>  words = new ArrayList<String>();
		List<Term> terms = DicAnalysis.parse(sentence).getTerms();
//		for(int i=0;i<terms.size();i++)
//		{
//			words.add(terms.get(i).getName());
//		}
		return terms;
	}


}
