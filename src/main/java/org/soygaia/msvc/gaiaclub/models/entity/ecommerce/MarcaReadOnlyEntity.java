package org.soygaia.msvc.gaiaclub.models.entity.ecommerce;


import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Entity
@Table(name = "t_marca")
@Immutable
public class MarcaReadOnlyEntity {
    @Id
    @Column(name = "mrc_id")
    private Long mrcId;
    @Column(name = "mrc_nombre")
    private String mrcNombre;
    @Column(name = "mrc_slug")
    private String mrcSlug;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prdMarca", fetch = FetchType.LAZY)
    private List<ProductoReadOnlyEntity> lstProducto;

    public Long getMrcId() {
        return mrcId;
    }

    public String getMrcNombre() {
        return mrcNombre;
    }

    public String getMrcSlug() {
        return mrcSlug;
    }
}
