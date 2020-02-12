package oracle.exam;

public class Exam_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OracleSQL oracle = new OracleSQL();
		try {
			oracle.makeConn();
			oracle.printDeptList();
			oracle.takeDown();
		} catch (Exception e) {
			System.out.print("연결 실패");
			e.printStackTrace();
		}
	}

}
