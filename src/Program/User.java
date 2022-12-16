package Program;



import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import UserDao.ENROLL_DAO;
import UserDao.H_MEMBER_DAO;
import UserDao.MEMBER_JOIN_TRAINER_DAO;
import UserDao.PRODUCT_DAO;
import UserDao.TRAINER_DAO;
import UserVo.ENROLL_VO;
import UserVo.H_MEMBER_JOIN_TRAINER_VO;
import UserVo.H_MEMBER_VO;
import UserVo.PRODUCT_VO;
import UserVo.TRANINER_VO;

public class User {
	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
		//****************************변수선언*********************/
		Scanner sc= new Scanner(System.in);
		int Tno,Pno;
		String name,pw,gender,phone,Bdate,id;
		H_MEMBER_DAO dao=H_MEMBER_DAO.getMemberDao();
		TRAINER_DAO dao2=TRAINER_DAO.getTrainerDao();
		MEMBER_JOIN_TRAINER_DAO dao3=MEMBER_JOIN_TRAINER_DAO.getMTJ();
		PRODUCT_DAO dao4= PRODUCT_DAO.getPRODUCT_DAO();
		ENROLL_DAO dao5=ENROLL_DAO.getEnrollDao();
		List<TRANINER_VO> list = dao2.trainerInfo(); 
		List<PRODUCT_VO> list2 = dao4.productInfo(); 
		boolean run=true;
		/************************************************************/
		while(run) {
			System.out.println(" □+---------------------------------------------------------------------------------------------------------+□");
			System.out.println(" □ | 1.회원 등록| |2.로그인| | 3.회원 정보수정| |4.회원 정보 보기| | 5.PT권 구매 | |6.프로그램 종료| □");
			System.out.println(" □+---------------------------------------------------------------------------------------------------------+□");
			System.out.print("원하는 메뉴를 선택하세요:");
			int num=Integer.parseInt(sc.nextLine());
			switch(num){
			case 1:
				System.out.println("회원 등록을 시작하겠습니다!!");
				System.out.print("이름을 입력:");
				name=sc.nextLine();
				System.out.print("아이디를 입력:");
				id=sc.nextLine();
				if(!dao.checkId(id)) {
					System.out.println("이미 사용중인 아이디입니다. 다시 입력해 주세요");
					break;
				}
				System.out.print("비밀 번호를 입력해:");
				pw=sc.nextLine();
				System.out.print("전화번호를 입력:");
				phone=sc.nextLine();
				System.out.println("성별을 입력해(M,W):");
				gender=sc.nextLine();
				System.out.println("생년월일 입력(8자리):");
				Bdate=sc.nextLine();
				System.out.println("⭕⭕⭕트레이너 리스트⭕⭕⭕\n");
				for(TRANINER_VO t:list) 
					System.out.println(t.toString());
				System.out.println("원하시는 트레이너를 선택하여 번호를 입력해 주세요.");
				System.out.print("선택:");
				Tno=Integer.parseInt(sc.nextLine());
				boolean check=false;
				for(int i=0;i<list.size();i++) {
				if(Tno==list.get(i).getTRAINER_NO()) {check=true;}
				}if(!check) { System.out.println("해당 트레이너는 없는 트레이너 입니다 번호를 다시 확인 후 입력해주세요.");}
				else {System.out.println(Tno+"번 트레이너가 선택되었습니다.");}
				//회원 정보 삽입
				if(dao.insert(new H_MEMBER_VO(0, Tno, id, dao.bytesToHex(dao.sha256(pw)), name, phone, gender, Bdate)))
				{System.out.println("회원가입이 완료되었습니다.");}
				else {System.out.println("회원가입이 실패했습니다.");}
				break;
			case 2:
				System.out.print("아이디를 입력:");
				id=sc.nextLine();
				System.out.println("비밀번호 입력");
				pw=sc.nextLine();
				if(	dao.login(id, pw)){System.out.println("로그인 성공");}
				else {System.out.println("로그인 실패");}
				break;
			case 3:	
				int up=0;
				System.out.println("회원정보를 수정합니다.");
				System.out.println("1.전화번호 수정 2. 비밀번호 수정");
				up=Integer.parseInt(sc.nextLine());
				if(up==1) {
					System.out.println("전화번호를 수정합니다.");
					System.out.print("아이디를 입력:");
					id=sc.nextLine();
					if(dao.checkId(id))System.out.println("해당 아이디는 존재하지 않습니다.");
					System.out.print("바꿀 전화번호 입력:");
					phone=sc.nextLine();
					if(dao.UpdatePh(phone, id)) {System.out.println("전화번호 수정이 완료되었습니다.");}
					else {System.out.println("전화번호 수정을 실패했습니다.");}
				}else if(up==2) {
					System.out.println("비밀번호를 수정합니다.");
					System.out.println("아이디 입력");
					id=sc.nextLine();
					if(dao.checkId(id))System.out.println("해당 아이디는 존재하지 않습니다.");
					System.out.print("바꿀 비밀번호 입력:");
					pw=sc.nextLine();
					System.out.print("비밀번호 확인:");
					if(!pw.equals(sc.nextLine())) {System.out.println("잘못 입력하였습니다.");}
					if(	dao.UpdatePw(dao.bytesToHex(dao.sha256(pw)), id)) {
						System.out.println("비밀번호 변경 성공");
					}else {System.out.println("비밀번호 변경 실패");}
				}else {System.out.println("잘못 입력하였습니다.");}
				break;
			case 4:
				System.out.println("회원님의 정보 보기");
				System.out.print("회원님의 아이디를 입력해 주세요:");
				id=sc.nextLine();
				if(dao.checkId(id))System.out.println("해당 아이디는 존재하지 않습니다.");
				else {System.out.println(dao3.memberInfo(id));}
				break;		
			case 5:
				System.out.println("+-------------------------상품권 구매-------------------------------------------------------------------------------------------+");
				for(PRODUCT_VO vo:list2) 
					System.out.println("| |\t\t상품 번호 :"+vo.getPRODUCT_NO()
												+"\t\t상품 이름 :"+vo.getNAME()
												+"\t\t상품 기간 :"+vo.getPERIOD()
												+"\t\t상품 가격 :"+vo.getPRICE()+"원| |"
							);
				System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+\n");
				System.out.print("회원 아이디를 입력해 주세요:");
				id=sc.nextLine();
				if(dao.checkId(id)) {System.out.println("해당 아이디는 없는 아이디 입니다. 다시 입력해 주세요");break;} 
				System.out.print("원하시는 상품 번호를 입력해 주세요:");
				Pno=Integer.parseInt(sc.nextLine());
				dao5.insertEnroll(Pno, id);
				System.out.println(dao4.SelectOneProduct(Pno).toString());
				break;
			case 6:
				System.out.println("프로그램이 종료되었습니다.");
				break;
			default :
				System.out.println("없는 번호입니다. 다시 입력하시던가하세요");
			}//switch end
		}//while end
	} // main end
}//end
