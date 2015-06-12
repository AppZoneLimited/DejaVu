package dejavu.appzonegroup.com.dejavuandroid.Models;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class DebitCardModel {
    private String cardNumber;
    private String cvv;
    private String pin;
    private String expDate;

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getPin() {
        return pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpDate() {
        return expDate;
    }
}
