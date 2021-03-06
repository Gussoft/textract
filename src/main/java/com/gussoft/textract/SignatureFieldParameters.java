package com.gussoft.textract;

import java.io.Serializable;

public class SignatureFieldParameters implements Serializable {

    private static final long serialVersionUID = 4032195150617714778L;

    /** Signature field id/name (optional) */
    private String fieldId;

    /** Page number where the signature field is added */
    private int page = 1;//ImageUtils.DEFAULT_FIRST_PAGE;

    /** Coordinate X where to add the signature field (origin is top/left corner) */
    private float originX;

    /** Coordinate Y where to add the signature field (origin is top/left corner) */
    private float originY;

    /** Signature field width */
    private float width;

    /** Signature field height */
    private float height;

    /**
     * Gets signature field id
     *
     * @return {@link String} signature field id
     */
    public String getFieldId() {
        return fieldId;
    }

    /**
     * Sets a signature field id/name to place a signature into
     *
     * @param fieldId {@link String} signature field id/name
     */
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * Gets a page where the signature should be placed
     *
     * @return page number
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets a page number where the signature field should be placed
     *
     * NOTE: the counting starts from 1 (one) for the first page of the document
     *
     * @param page where the signature field should be placed
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets an upper left X coordinate
     *
     * @return upper left X coordinate
     */
    public float getOriginX() {
        return originX;
    }

    /**
     * Sets a upper left X coordinate of the signature field
     *
     * @param originX upper left X coordinate
     */
    public void setOriginX(float originX) {
        this.originX = originX;
    }

    /**
     * Gets a upper left Y coordinate
     *
     * @return upper left Y coordinate
     */
    public float getOriginY() {
        return originY;
    }

    /**
     * Sets a upper left Y coordinate of the signature field
     *
     * @param originY upper left Y coordinate
     */
    public void setOriginY(float originY) {
        this.originY = originY;
    }

    /**
     * Gets a width of the signature field
     *
     * @return width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Sets a width of the signature field
     *
     * @param width of the signature field
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Gets a height of the signature field
     *
     * @return height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets a height of the signature field
     *
     * @param height of the signature field
     */
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "SignatureFieldParameters [name=" + fieldId + ", page=" + page + ", originX=" + originX + ", originY="
                + originY + ", width=" + width + ", height=" + height + "]";
    }

}
