package com.example.satselva.mytestapp;

import java.util.Date;

/**
 * Created by satselva on 2/10/2016.
 */
public class Category {

    private String id;

    private String name;

    private String iconUrl;

    private String createdBy;

    private String modifiedBy;

    private Date modifiedDate;

    private Date createdDate;

    private String v;

    private Category(CategoryBuilder builder) {
        this.id = builder.id;
        this.iconUrl = builder.iconUrl;
        this.createdBy = builder.createdBy;
        this.modifiedBy = builder.modifiedBy;
        this.modifiedDate = builder.modifiedDate;
        this.createdDate = builder.createdDate;
        this.name = builder.name;
        this.v = builder.v;
    }

    public String getId() {
        return id;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getName() {
        return name;
    }

    public String getV() {
        return v;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", createdDate=" + createdDate +
                ", name='" + name + '\'' +
                ", V='" + v + '\'' +
                '}';
    }

    public static final class CategoryBuilder {
        private String id;

        private String name;

        private String iconUrl;

        private String createdBy;

        private String modifiedBy;

        private Date modifiedDate;

        private Date createdDate;

        private String v;

        public CategoryBuilder( ) {
        }

        public CategoryBuilder withId(final String id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder withIconUrl(final String iconUrl) {
            this.iconUrl = iconUrl;
            return this;
        }

        public CategoryBuilder withCreatedBy(final String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public CategoryBuilder withModifiedBy(final String modifiedBy) {
            this.modifiedBy = modifiedBy;
            return this;
        }

        public CategoryBuilder withCreatedDate(final Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public CategoryBuilder withModifiedDate(final Date modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }

        public CategoryBuilder withV(final String version) {
            this.v = version;
            return this;
        }

        public Category build( ) {
            Category category = new Category(this);
            return category;
        }
    }
}
