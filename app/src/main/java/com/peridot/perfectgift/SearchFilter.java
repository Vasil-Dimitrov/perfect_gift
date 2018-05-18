package com.peridot.perfectgift;

import android.text.TextUtils;

public class SearchFilter {
    String selectedGender;
    String selectedAge;
    String selectedCategory;
    Double fromPrice;
    Double toPrice;

    public SearchFilter() {}

    public SearchFilter(android.content.Intent intent) {
        setSelectedGender(intent.getExtras().getString("com.peridot.perfectgift.selectedGender"));
        setSelectedAge(intent.getExtras().getString("com.peridot.perfectgift.selectedAge"));
        setSelectedCategory(intent.getExtras().getString("com.peridot.perfectgift.selectedCategory"));
        setFromPrice(intent.getExtras().getDouble("com.peridot.perfectgift.fromPrice"));
        setToPrice(intent.getExtras().getDouble("com.peridot.perfectgift.toPrice"));
    }

    public String getSelectedGender() {
        return selectedGender;
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    public String getSelectedAge() {
        return selectedAge;
    }

    public void setSelectedAge(String selectedAge) {
        this.selectedAge = selectedAge;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Double getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(Double fromPrice) {
        this.fromPrice = fromPrice;
    }

    public void setFromPrice(String fromPriceString) {
        this.fromPrice = getDoubleValue(fromPriceString);
    }

    public Double getToPrice() {
        return toPrice;
    }

    public void setToPrice(Double toPrice) {
        this.toPrice = toPrice;
    }

    public void setToPrice(String toPriceString) {
        this.toPrice = getDoubleValue(toPriceString);
    }

    private Double getDoubleValue(String doubleString) {
        if(!TextUtils.isEmpty(doubleString)) {
            try {
                return Double.parseDouble(doubleString);
            } catch (NumberFormatException e) {
                return (double) -1;
            }
        } else {
            return null;
        }
    }

    public String validate() {
        StringBuilder str = new StringBuilder();
        if(TextUtils.isEmpty(selectedGender)) {
            str.append("Моля изберете пол!\n");
        }
        if(TextUtils.isEmpty(selectedAge)) {
            str.append("Моля изберете възрастова граница!\n");
        }
        if(TextUtils.isEmpty(selectedCategory)) {
            str.append("Моля изберете категория!\n");
        }
        if(fromPrice != null && fromPrice < 0) {
            str.append("\"От цена\" - невалидна стойност!\n");
        }
        if(toPrice != null && toPrice <= 0) {
            str.append("\"До цена\" - невалидна стойност!\n");
        }
        if(fromPrice != null && toPrice != null && fromPrice > toPrice) {
            str.append("\"От цена\" не може да бъде по-голяма от \"До цена\"!\n");
        }
        return str.toString();
    }
}
