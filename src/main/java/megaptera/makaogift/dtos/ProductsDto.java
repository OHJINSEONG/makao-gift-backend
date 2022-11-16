package megaptera.makaogift.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> productDtos;
    private List<PageDto> pageDtos;


    public ProductsDto(List<ProductDto> productDtos, List<PageDto> pageDtos) {
        this.productDtos = productDtos;
        this.pageDtos = pageDtos;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public List<PageDto> getPageDtos() {
        return pageDtos;
    }
}
