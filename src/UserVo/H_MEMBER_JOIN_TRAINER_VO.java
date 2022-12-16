package UserVo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class H_MEMBER_JOIN_TRAINER_VO {
private String name;
private int MEMBER_NO;
private String ID;
private String PHONE;
private String T_NAME;
private String BIRTH_DATE;
	
@Override
public String toString() {
	return String.format("%s님의 회원정보 => 회원 번호:%d || 아이디: %s || 전화번호: %s || 담당 트레이너 %s || 생년월일 : %s"
			,name,MEMBER_NO,ID,PHONE,T_NAME,BIRTH_DATE );
}

}
