package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.Postal_code;
import adminNew.temelEkran.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentSchoolService {

    @Autowired
    private SchoolService sService;
    @Autowired
    private PostalCodeService pService;

    private static final double R = 6371;
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }



    public List<School> getCloseSchools(int maxDistance, int postal_code){
        int i = 0;
        double distance = 0;
        List<School> src = new ArrayList<School>();
        Postal_code p = pService.getAdressByPostalCode(postal_code);
        if(p != null){
            System.out.println("Baba elemanın posta kodu taklaya getirdi bizi");
        }
        List<School> allSchool = sService.getAllSchool();
        if(allSchool != null){
            System.out.println("Baba okul kodu taklaya getirdi bizi");
        }
        while(i < allSchool.size()){
            System.out.println("Döngüye Girdim: " + i + ". kez");

            School s = allSchool.get(i);
            System.out.println(s.getPostal_code());
            Postal_code temp = pService.getAdressByPostalCode(s.getPostal_code());
            distance = calculateDistance(p.getLatitude().doubleValue(), p.getLongitude().doubleValue(),
                    temp.getLatitude().doubleValue(),temp.getLongitude().doubleValue());
            if(distance < maxDistance){
                System.out.println("sen bizim için bir babasın");

                src.add(s);
            }
            i++;
        }
        System.out.println("ÇIKTIM");
        return src;
    }


}
