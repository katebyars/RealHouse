package com.example.guest.realhouse.models;

import org.parceler.Parcel;

@Parcel
public class House {

    String crossStreet;
    String state;
    String country;
    String postalCode;
    String streetName;
    String streetNumberText;
    String city;
    String streetNumber;
    String fullAddress;
    String listDate;
    String agentLastName;
    String agentEmail;
    String agentOffice;
    String agentCell;
    String agentFirstName;
    String photos;
    String listPrice;
    String listingId;
    String daysOnMarket;
    String startTime;
    String endTime;
    String description;
    String refreshments;
    private String pushId;

    public House() {}

    public House(String agentFirstName, String streetName) {


        this.crossStreet = crossStreet;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNumberText = streetNumberText;
        this.city = city;
        this.streetNumber = streetNumber;
        this.fullAddress = fullAddress;
        this.listDate = listDate;
        this.agentLastName = agentLastName;
        this.agentEmail = agentEmail;
        this.agentOffice = agentOffice;
        this.agentCell = agentCell;
        this.agentFirstName = agentFirstName;
        this.photos = photos;
        this.listPrice = listPrice;
        this.listingId = listingId;
        this.daysOnMarket = daysOnMarket;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.refreshments = refreshments;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumberText() {
        return streetNumberText;
    }

    public void setStreetNumberText(String streetNumberText) {
        this.streetNumberText = streetNumberText;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getAgentLastName() {
        return agentLastName;
    }

    public void setAgentLastName(String agentLastName) {
        this.agentLastName = agentLastName;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public String getAgentOffice() {
        return agentOffice;
    }

    public void setAgentOffice(String agentOffice) {
        this.agentOffice = agentOffice;
    }

    public String getAgentCell() {
        return agentCell;
    }

    public void setAgentCell(String agentCell) {
        this.agentCell = agentCell;
    }

    public String getAgentFirstName() {
        return agentFirstName;
    }

    public void setAgentFirstName(String agentFirstName) {
        this.agentFirstName = agentFirstName;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getDaysOnMarket() {
        return daysOnMarket;
    }

    public void setDaysOnMarket(String daysOnMarket) {
        this.daysOnMarket = daysOnMarket;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRefreshments() {
        return refreshments;
    }

    public void setRefreshments(String refreshments) {
        this.refreshments = refreshments;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
