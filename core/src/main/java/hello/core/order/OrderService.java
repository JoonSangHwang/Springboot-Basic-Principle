package hello.core.order;

public interface OrderService {

    /**
     * 주문생성
     *
     * @param memberId      회원 id
     * @param itemName      상품 명
     * @param itemPrice     상품 가격
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
