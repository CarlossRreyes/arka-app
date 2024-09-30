DROP FUNCTION IF EXISTS listar_productos_disponibles();

CREATE OR REPLACE FUNCTION listar_productos_disponibles()
RETURNS TABLE(
    product_id UUID,
    code character varying(10),
    name character varying(80),
    description character varying(150),
    units_in_stock INTEGER,
    is_available BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        tb_product.product_id, 
        tb_product.code, 
        tb_product.name, 
        tb_product.description, 
        tb_product.units_in_stock, 
        tb_product.is_available, 
        tb_product.created_at, 
        tb_product.updated_at
    FROM 
        tb_product
    WHERE 
        tb_product.is_available = true;
END;
$$ LANGUAGE plpgsql;


SELECT * FROM listar_productos_disponibles();


//get_packages_by_category
CREATE OR REPLACE FUNCTION get_packages_by_category(category_uuid UUID)
RETURNS TABLE(
    package_id UUID,
    total_units INTEGER,
    total_price NUMERIC,
    is_promotion BOOLEAN,
    is_available BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        p.package_id, 
        p.total_units, 
        p.total_price, 
        p.is_promotion, 
        p.is_available, 
        p.created_at, 
        p.updated_at
    FROM 
        tb_package AS p
    JOIN 
        tb_package_detail AS pd ON p.package_id = pd.package_id
    JOIN 
        tb_product AS pr ON pd.product_id = pr.product_id
    JOIN 
        tb_product_category AS pc ON pr.product_id = pc.product_id
    JOIN 
        tb_category AS c ON pc.category_id = c.category_id
    WHERE c.category_id = category_uuid;
END;
$$ LANGUAGE plpgsql;


SELECT * FROM get_packages_by_category('f72dae79-82c8-41b7-9304-fb554bc63c51');



new 
CREATE OR REPLACE FUNCTION get_packages_by_category(
    category_uuid UUID,
    limit_count INTEGER,
    offset_count INTEGER
)
RETURNS TABLE(
    package_id UUID,
    total_units INTEGER,
    total_price NUMERIC,
    is_promotion BOOLEAN,
    is_available BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
) AS $$
BEGIN
    RETURN QUERY
    SELECT DISTINCT ON (p.package_id)
        p.package_id, 
        p.total_units, 
        p.total_price, 
        p.is_promotion, 
        p.is_available, 
        p.created_at, 
        p.updated_at
    FROM 
        tb_package AS p
    JOIN 
        tb_package_detail AS pd ON p.package_id = pd.package_id
    JOIN 
        tb_product AS pr ON pd.product_id = pr.product_id
    JOIN 
        tb_product_category AS pc ON pr.product_id = pc.product_id
    JOIN 
        tb_category AS c ON pc.category_id = c.category_id
    WHERE c.category_id = category_uuid
    ORDER BY p.package_id, p.created_at DESC
    LIMIT limit_count
    OFFSET offset_count;
END;
$$ LANGUAGE plpgsql;



---Find all categories by business id
CREATE OR REPLACE FUNCTION get_categories_by_business(
    business_uuid UUID,
    limit_count INTEGER,
    offset_count INTEGER
)
RETURNS TABLE(
    category_id UUID,
    code VARCHAR,
    name VARCHAR,
    description VARCHAR,
    image_path VARCHAR,
    is_active BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    business_id UUID
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        c.category_id, 
        c.code, 
        c.name, 
        c.description, 
        c.image_path, 
        c.is_active, 
        c.created_at, 
        c.updated_at,
        c.business_id
    FROM 
        tb_category AS c
    JOIN 
        tb_business AS b ON c.business_id = b.business_id
    WHERE 
        b.business_id = business_uuid
    ORDER BY 
        c.created_at DESC
    LIMIT 
        limit_count
    OFFSET 
        offset_count;
END;
$$ LANGUAGE plpgsql;
