import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class IntercomTakeHome {

    private static final CoordinateLocation intercomLocation = new CoordinateLocation(53.339428, -6.257664);
    private static final double partyRadius = 100000;
    private static final String url = "https://gist.githubusercontent.com/brianw/19896c50afa89ad4dec3/raw/6c11047887a03483c50017c1d451667fd62a53ca/gistfile1.txt";

    public static void main(String[] args) throws IOException {
        IntercomGuestList guestList = new IntercomGuestList();

        String jsonUrl;
        if(args.length == 0 || args[0] != null){
            jsonUrl = url;
        } else {
            jsonUrl = args[0];
        }
        String customerJson = JSONFixer.readCustomerJsonFile(jsonUrl);

        guestList.buildGuestList(customerJson, partyRadius, intercomLocation);
        List<IntercomCustomer> partyList = guestList.getGuestList();

        try(FileOutputStream outputStream = new FileOutputStream("guestlist.txt")){
            StringBuilder output = new StringBuilder();
            for(IntercomCustomer customer : partyList){
                double distance = guestList.getDistanceToParty(customer, intercomLocation);
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                output.append(customer + "; Distance: " + formatter.format(distance/1000) + " km \n");
            }
            byte[] outputBytes = output.toString().getBytes();
            outputStream.write(outputBytes);

        }


    }

}
