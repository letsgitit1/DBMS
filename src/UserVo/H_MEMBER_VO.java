package UserVo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class H_MEMBER_VO {
	private int MEMBER_NO;
	private int TRAINER_NO;
	private String ID;
	private String PW;
	private String name;
	private String PHONE;
	private String GENDER;
	private String  BIRTH_DATE;
}