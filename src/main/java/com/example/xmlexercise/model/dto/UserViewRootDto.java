package com.example.xmlexercise.model.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserViewRootDto {

    @XmlElement(name = "product")
    private List<UserWithProductsDto> products;

    public List<UserWithProductsDto> getProducts() {
        return products;
    }

    public void setProducts(List<UserWithProductsDto> products) {
        this.products = products;
    }
}
