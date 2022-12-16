package UserVo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PRODUCT_VO {
private int PRODUCT_NO;
private String NAME;
private int PERIOD;
private int PRICE;
@Override
	public String toString() {
		return PRODUCT_NO+"번 상품이 선택 되었습니다. 해당 상품의 가격은: "+PRICE+"원 잔여 횟수 :"+PERIOD+"입니다!";
	}
}
