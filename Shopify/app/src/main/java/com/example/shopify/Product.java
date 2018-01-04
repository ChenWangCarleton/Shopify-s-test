package com.example.shopify;


public class Product {
    private String id;
    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private String created_at;
    private String handle;
    private String updated_at;
    private String publicshed_at;
    private String template_suffix;
    private String published_scope;
    private String tags;
    private String img;

    public Product(String id, String title, String body_html, String vendor, String product_type, String created_at, String handle, String updated_at, String publicshed_at, String template_suffix, String published_scope, String tags, String img) {
        this.id = id;
        this.title = title;
        this.body_html = body_html;
        this.vendor = vendor;
        this.product_type = product_type;
        this.created_at = created_at;
        this.handle = handle;
        this.updated_at = updated_at;
        this.publicshed_at = publicshed_at;
        this.template_suffix = template_suffix;
        this.published_scope = published_scope;
        this.tags = tags;
        this.img = img;
    }
    public Product(Product p){
        this.id=p.id;
        this.title=p.title;
        this.body_html=p.body_html;
        this.vendor=p.vendor;
        this.product_type=p.product_type;
        this.created_at=p.created_at;

        this.handle=p.handle;
        this.updated_at=p.updated_at;
        this.publicshed_at=p.publicshed_at;
        this.template_suffix=p.template_suffix;
        this.published_scope=p.published_scope;
        this.tags=p.tags;
        this.img=p.img;
    }
    public Product(){
    }
    @Override
    public String toString() {
        return
                "id: " + id  +
                "\nbody_html: " + body_html  +
                "\nvendor: " + vendor  +
                "\nproduct_type: " + product_type +
                "\ncreated_at: " + created_at +
                "\nhandle: " + handle +
                "\nupdated_at: " + updated_at +
                "\npublicshed_at: " + publicshed_at +
                "\ntemplate_suffix: " + template_suffix +
                "\npublished_scope: " + published_scope +
                "\ntags: " + tags ;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPublicshed_at() {
        return publicshed_at;
    }

    public void setPublicshed_at(String publicshed_at) {
        this.publicshed_at = publicshed_at;
    }

    public String getTemplate_suffix() {
        return template_suffix;
    }

    public void setTemplate_suffix(String template_suffix) {
        this.template_suffix = template_suffix;
    }

    public String getPublished_scope() {
        return published_scope;
    }

    public void setPublished_scope(String published_scope) {
        this.published_scope = published_scope;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {

        return title;
    }
}
