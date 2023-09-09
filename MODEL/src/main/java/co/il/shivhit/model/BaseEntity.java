package co.il.shivhit.model;

import java.io.Serializable;
import java.util.Objects;

public class BaseEntity implements Serializable {
    protected String idFs;

    public BaseEntity(){}

    public String getIdFs() {
        return idFs;
    }

    public void setIdFs(String idFs) {
        this.idFs = idFs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(idFs, that.idFs);
    }
}
