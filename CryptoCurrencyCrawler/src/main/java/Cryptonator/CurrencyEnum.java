package Cryptonator;

/**
 * Created by Lennard on 18-7-2017.
 */
public enum CurrencyEnum {
    // Declare currencies
    USD ("usd"),
    EUR ("eur"),
    JPY ("jpy"),

    // Declare cryptocurrencies
    BTC ("btc"),
    ETH ("eth"),
    XRP ("xrp"),
    LTC ("ltc"),
    ETC ("etc"),
    XEM ("xem"),
    DASH ("dash"),
    MIOTA ("miota"),
    XMR ("xmr"),
    VERI ("veri");


    private final String code;

    CurrencyEnum(String code)
    {
        this.code = code;
    }

    public String code()
    {
        return this.code;
    }

    public String generateRate(CurrencyEnum currency)
    {
        return this.code + "_" + currency.code;
    }
}
