package megaptera.makaogift.models.Qclass;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import megaptera.makaogift.models.Product;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

public class QProduct extends EntityPathBase<Product> {
    private static final long serialVersionUID = 1L;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Long> id = createNumber("id", Long.class);
    public final StringPath title = createString("title");
    public final NumberPath<Double> price = createNumber("price", Double.class);
    // Add other properties of the Product entity as necessary

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }
}