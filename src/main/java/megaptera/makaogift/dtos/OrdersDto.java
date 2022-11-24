package megaptera.makaogift.dtos;

import java.util.List;

public class OrdersDto {
    private List<OrderDto> orderDtos;
    private List<PageDto> pageDtos;

    public OrdersDto(List<OrderDto> orderDtos, List<PageDto> pageDtos) {
        this.orderDtos = orderDtos;
        this.pageDtos = pageDtos;
    }

    public List<OrderDto> getOrderDtos() {
        return orderDtos;
    }

    public List<PageDto> getPageDtos() {
        return pageDtos;
    }
}
