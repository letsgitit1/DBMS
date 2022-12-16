package UserVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
@Getter
@AllArgsConstructor
	public class TRANINER_VO {
		private int TRAINER_NO;
		private String NAME;
		private String GENDER;
		private String PHONE;
		private String CARRER;
		@Override
		public String toString() {
			return  "| 트레이너 번호:"+TRAINER_NO+"| |트레이너 이름:"+ NAME + "| |성별:" + GENDER +" |\n"+ "경력:" + CARRER;
		}
		
}
