package customer;

import java.util.ArrayList;
import java.util.List;

import customer.model.CusDao;
import customer.model.CusVO;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CusDao cusDao = new CusDao();
		

		List<String> wheres = new ArrayList<String>();
		wheres.add("int,num,1");

		List<CusVO> list = (List<CusVO>)cusDao.query("SELECT *", "CUSTOM", wheres);
		
		for(CusVO aa : list) {
			System.out.print(aa.getNum() + "\t");
			System.out.print(aa.getName() + "\t");
			System.out.println();
		}
		
		System.out.println(list);
	}

}
