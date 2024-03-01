package helpers;

public class RequestSpecificationCreator {
    public String uuid;
    public String baseUri = "v3.dev.regagro.ru";
    public String pathAddress = "/ajax/address/object/";
    public String pathDistrict = uuid + "/children?levels=district";
    public String pathCity = uuid + "/children?levels=city,locality";
    public String pathStreet = uuid + "/children?levels=street";




}
