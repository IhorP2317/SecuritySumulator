package com.securitySimulator.seeder;

import com.securitySimulator.helpers.RoomHelper;
import com.securitySimulator.model.entities.Apartment;
import com.securitySimulator.model.entities.Building;
import com.securitySimulator.model.entities.Floor;
import com.securitySimulator.model.entities.Room;
import com.securitySimulator.model.enums.NormativeType;
import com.securitySimulator.model.user.ERole;
import com.securitySimulator.model.user.Role;
import com.securitySimulator.model.user.User;
import com.securitySimulator.repository.*;
import jakarta.annotation.PostConstruct;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Component
public class DataSeeder{

    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    FloorRepository floorRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    PasswordEncoder encoder;

    static Random random = new Random();

    @PostConstruct
    public void run() {
        List<Room> rooms = new ArrayList<>();
        NormativeType normativeType;
        for (int i = 0; i < 200; i++){
            if(i % 2 == 0){
                normativeType = NormativeType.Common;
            } else {
                if(i % 5 == 0){
                    normativeType = NormativeType.Advanced;
                }else{
                    normativeType = NormativeType.Premium;
                }
            }
            var newroom = new Room(random.nextDouble(20, 100), random.nextInt(1,5), random.nextInt(1,10), normativeType);
            RoomHelper.populateSensorsForRoom(newroom);
            rooms.add(newroom);
        }



        for(int i = 0; i < rooms.size(); i++){
            RoomHelper.populateSensorsForRoom(rooms.get(i));
        }



        List<Floor> floors = new ArrayList<>();

        for(int tmpFrom = 0, tmpTo = 0; tmpTo < rooms.size();){
            tmpTo = random.nextInt(tmpFrom + 1, tmpFrom + 4);
            if(tmpTo >= rooms.size() - 4){
                tmpTo = rooms.size();
            }

            var newfloor = new Floor(random.nextInt(1, 12));
            var roomlist = rooms.subList(tmpFrom, tmpTo);

            roomlist.forEach(r -> r.setFloor(newfloor));

            newfloor.setRooms(roomlist);
            floors.add(newfloor);
            tmpFrom = tmpTo;
        }


        List<Apartment> apartments = new ArrayList<>();

        for(int tmpFrom = 0, tmpTo = 0; tmpTo < floors.size();){
            tmpTo = random.nextInt(tmpFrom + 1, tmpFrom + 4);
            if(tmpTo >= floors.size() - 4){
                tmpTo = floors.size();
            }

            var newapartment = new Apartment();
            var floorlist = floors.subList(tmpFrom, tmpTo);

            floorlist.forEach(f -> f.setApartment(newapartment));
            newapartment.setFloors(floorlist);
            apartments.add(newapartment);
            tmpFrom = tmpTo;
        }


        List<Building> buildings = new ArrayList<>();

        for(int tmpFrom = 0, tmpTo = 0; tmpTo < apartments.size();){
            tmpTo = random.nextInt(tmpFrom + 1, tmpFrom + 4);
            if(tmpTo >= apartments.size() - 4){
                tmpTo = apartments.size();
            }

            var newbuilding = new Building("address", getRandomBigDecimalInRange(new BigDecimal("49.8222835"),new BigDecimal("49.8523979")), getRandomBigDecimalInRange(new BigDecimal("24.0210356"), new BigDecimal("24.0485014")));
            var apartlist = apartments.subList(tmpFrom, tmpTo);

            apartlist.forEach(a -> a.setBuilding(newbuilding));

            newbuilding.setApartments(apartlist);

            buildings.add(newbuilding);
            tmpFrom = tmpTo;
        }

        List<User> users = new ArrayList<>();

        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ERole.ROLE_ADMIN));
        roles.add(new Role(ERole.ROLE_USER));
        roles.add(new Role(ERole.ROLE_MODERATOR));

        roleRepository.saveAll(roles);

        roles = roleRepository.findAll();
        var userData = new ArrayList<>(
                Arrays.asList(
                        new Pair<String, String>(
                                "Caryn Webb",
                                "sapien.imperdiet.ornare@yahoo.com"
                        ),
                        new Pair<String, String>(
                                "Chantale Sharp",
                                "quam.curabitur@aol.net"
                        ),
                        new Pair<String, String>(
                                "Shad Mcintosh",
                                "vestibulum@icloud.edu"
                        ),
                        new Pair<String, String>(
                                "Pandora Irwin",
                                "feugiat.sed@google.com"
                        ),
                        new Pair<String, String>(
                                "Jesse Head",
                                "erat.vel.pede@hotmail.net"
                        ),
                        new Pair<String, String>(
                                "Xander Day",
                                "sed@hotmail.com"
                        ),
                        new Pair<String, String>(
                                "Conan Peters",
                                "ac.risus@hotmail.edu"
                        ),
                        new Pair<String, String>(
                                "Walter Weber",
                                "mus@icloud.edu"
                        ),
                        new Pair<String, String>(
                                "Cathleen Rogers",
                                "consequat@hotmail.com"
                        ),
                        new Pair<String, String>(
                                "Deanna Reilly",
                                "dolor@protonmail.net"
                        ),
                        new Pair<String, String>(
                                "Quentin Powell",
                                "montes@yahoo.ca"
                        ),
                        new Pair<String, String>(
                                "Allistair Dale",
                                "aliquet.lobortis@aol.edu"
                        ),
                        new Pair<String, String>(
                                "Keefe Casey",
                                "libero.donec.consectetuer@aol.edu"
                        ),
                        new Pair<String, String>(
                                "Ryder Sparks",
                                "proin.sed.turpis@outlook.couk"
                        ),
                        new Pair<String, String>(
                                "Vincent Wiley",
                                "eu.odio.tristique@protonmail.net"
                        ),
                        new Pair<String, String>(
                                "Kiara Jackson",
                                "nisi.mauris.nulla@icloud.org"
                        ),
                        new Pair<String, String>(
                                "Giacomo Spence",
                                "metus.aenean.sed@hotmail.ca"
                        ),
                        new Pair<String, String>(
                                "MacKensie Sweeney",
                                "at.nisi@google.ca"
                        ),
                        new Pair<String, String>(
                                "Bryar Branch",
                                "nec.luctus.felis@outlook.ca"
                        ),
                        new Pair<String, String>(
                                "Miriam Clark",
                                "lacus@protonmail.com"
                        ),
                        new Pair<String, String>(
                                "Tanner Cortez",
                                "risus.odio@protonmail.ca"
                        ),
                        new Pair<String, String>(
                                "Jillian Nixon",
                                "massa.quisque@aol.ca"
                        ),
                        new Pair<String, String>(
                                "Brandon Jordan",
                                "donec.egestas@protonmail.org"
                        ),
                        new Pair<String, String>(
                                "Cairo Sutton",
                                "ridiculus.mus.proin@hotmail.edu"
                        ),
                        new Pair<String, String>(
                                "Keane Beard",
                                "sed.orci@aol.com"
                        ),
                        new Pair<String, String>(
                                "Lance West",
                                "ullamcorper.duis.at@google.com"
                        ),
                        new Pair<String, String>(
                                "Amber Heath",
                                "quis.diam.luctus@yahoo.com"
                        ),
                        new Pair<String, String>(
                                "Felix Stark",
                                "nisl.arcu.iaculis@protonmail.org"
                        ),
                        new Pair<String, String>(
                                "Alvin Perkins",
                                "egestas.lacinia@protonmail.couk"
                        ),
                        new Pair<String, String>(
                                "Sarah Yang",
                                "mauris.vestibulum@outlook.net"
                        ),
                        new Pair<String, String>(
                                "Brooke Ortiz",
                                "velit@hotmail.couk"
                        ),
                        new Pair<String, String>(
                                "Christian Munoz",
                                "suspendisse@outlook.edu"
                        ),
                        new Pair<String, String>(
                                "Mary Whitehead",
                                "donec@aol.ca"
                        ),
                        new Pair<String, String>(
                                "Jerry Garrett",
                                "ipsum.primis@google.org"
                        ),
                        new Pair<String, String>(
                                "Denise Byrd",
                                "integer.aliquam@aol.edu"
                        ),
                        new Pair<String, String>(
                                "Talon Solomon",
                                "pellentesque.tellus@hotmail.org"
                        ),
                        new Pair<String, String>(
                                "Joseph Mckay",
                                "mi.pede@aol.edu"
                        ),
                        new Pair<String, String>(
                                "Piper Hardin",
                                "donec@yahoo.edu"
                        ),
                        new Pair<String, String>(
                                "Graham Barnett",
                                "molestie@icloud.edu"
                        ),
                        new Pair<String, String>(
                                "Kevin Valentine",
                                "cursus.non@protonmail.ca"
                        ),
                        new Pair<String, String>(
                                "Rinah Hester",
                                "nulla@protonmail.edu"
                        ),
                        new Pair<String, String>(
                                "Orli Brock",
                                "vehicula.risus@icloud.net"
                        ),
                        new Pair<String, String>(
                                "Jordan Merrill",
                                "consectetuer.ipsum.nunc@yahoo.net"
                        ),
                        new Pair<String, String>(
                                "Jarrod Ferrell",
                                "augue.eu@aol.ca"
                        ),
                        new Pair<String, String>(
                                "Briar Frazier",
                                "lacinia.mattis@google.com"
                        ),
                        new Pair<String, String>(
                                "Ryan Carlson",
                                "mi.eleifend.egestas@aol.com"
                        ),
                        new Pair<String, String>(
                                "Jin Hays",
                                "interdum.curabitur.dictum@protonmail.net"
                        ),
                        new Pair<String, String>(
                                "Malcolm Clay",
                                "risus.duis.a@yahoo.edu"
                        ),
                        new Pair<String, String>(
                                "Matthew Berry",
                                "justo.eu.arcu@icloud.edu"
                        ),
                        new Pair<String, String>(
                                "Stewart Carey",
                                "risus@aol.org"
                        ),
                        new Pair<String, String>(
                                "Tasha Potts",
                                "enim@google.com"
                        ),
                        new Pair<String, String>(
                                "Shaine Simmons",
                                "odio@aol.com"
                        ),
                        new Pair<String, String>(
                                "Grady Webster",
                                "hendrerit.a@aol.net"
                        ),
                        new Pair<String, String>(
                                "April Bond",
                                "non.dui.nec@aol.org"
                        ),
                        new Pair<String, String>(
                                "Paloma Vance",
                                "hendrerit@icloud.couk"
                        ),
                        new Pair<String, String>(
                                "Dana Hood",
                                "tortor@yahoo.net"
                        ),
                        new Pair<String, String>(
                                "Nissim Gray",
                                "quisque.nonummy.ipsum@yahoo.edu"
                        ),
                        new Pair<String, String>(
                                "Jacob Mendoza",
                                "diam@hotmail.net"
                        ),
                        new Pair<String, String>(
                                "Sharon Rosales",
                                "non.feugiat@aol.com"
                        ),
                        new Pair<String, String>(
                                "Amos Orr",
                                "lectus@yahoo.edu"
                        ),
                        new Pair<String, String>(
                                "Rebecca Washington",
                                "leo@hotmail.org"
                        ),
                        new Pair<String, String>(
                                "Iona Burch",
                                "iaculis@protonmail.ca"
                        ),
                        new Pair<String, String>(
                                "Drew Woodward",
                                "lacus.quisque.purus@yahoo.com"
                        ),
                        new Pair<String, String>(
                                "Carl Duncan",
                                "nam.consequat@outlook.couk"
                        ),
                        new Pair<String, String>(
                                "Pascale Williams",
                                "sit.amet@hotmail.edu"
                        ),
                        new Pair<String, String>(
                                "Noble Garrison",
                                "faucibus.morbi.vehicula@aol.couk"
                        ),
                        new Pair<String, String>(
                                "Levi Buck",
                                "enim.nec.tempus@hotmail.ca"
                        ),
                        new Pair<String, String>(
                                "Lee Knox",
                                "lobortis.class@protonmail.edu"
                        ),
                        new Pair<String, String>(
                                "Iliana Conway",
                                "fames.ac.turpis@outlook.ca"
                        ),
                        new Pair<String, String>(
                                "Jaime Chavez",
                                "molestie.in@aol.org"
                        ),
                        new Pair<String, String>(
                                "Baxter Page",
                                "rhoncus.id@google.couk"
                        ),
                        new Pair<String, String>(
                                "Naomi Benson",
                                "nec@aol.ca"
                        ),
                        new Pair<String, String>(
                                "Giacomo Bowers",
                                "facilisis.non.bibendum@protonmail.com"
                        ),
                        new Pair<String, String>(
                                "Xander Ortega",
                                "malesuada.fames@icloud.edu"
                        ),
                        new Pair<String, String>(
                                "Deacon Kirby",
                                "sit.amet@protonmail.com"
                        ),
                        new Pair<String, String>(
                                "Jameson Montgomery",
                                "tellus.eu.augue@icloud.ca"
                        ),
                        new Pair<String, String>(
                                "Omar Rodgers",
                                "nunc.quis.arcu@hotmail.ca"
                        ),
                        new Pair<String, String>(
                                "Wynter Fisher",
                                "sit.amet.ante@google.org"
                        ),
                        new Pair<String, String>(
                                "Wendy Mcclure",
                                "tincidunt.congue@google.couk"
                        ),
                        new Pair<String, String>(
                                "Amir Baker",
                                "nulla.ante@google.com"
                        ),
                        new Pair<String, String>(
                                "Shelly Alston",
                                "ligula.elit@outlook.org"
                        ),
                        new Pair<String, String>(
                                "Allen Salazar",
                                "justo@hotmail.org"
                        ),
                        new Pair<String, String>(
                                "Walker Chang",
                                "malesuada.fringilla@aol.couk"
                        ),
                        new Pair<String, String>(
                                "Rashad Mcpherson",
                                "ipsum@outlook.ca"
                        ),
                        new Pair<String, String>(
                                "Sonia Frost",
                                "dictum.proin.eget@outlook.ca"
                        ),
                        new Pair<String, String>(
                                "Noel Prince",
                                "nunc.ac.sem@outlook.edu"
                        ),
                        new Pair<String, String>(
                                "Todd Suarez",
                                "suspendisse.ac@outlook.edu"
                        ),
                        new Pair<String, String>(
                                "Ulysses Skinner",
                                "velit@icloud.org"
                        ),
                        new Pair<String, String>(
                                "Bruno Silva",
                                "rutrum@aol.org"
                        ),
                        new Pair<String, String>(
                                "Quyn Moses",
                                "dolor@aol.net"
                        ),
                        new Pair<String, String>(
                                "Justine Macias",
                                "magna.cras@yahoo.couk"
                        ),
                        new Pair<String, String>(
                                "Wanda Mendez",
                                "placerat.velit.quisque@yahoo.net"
                        ),
                        new Pair<String, String>(
                                "Venus Blanchard",
                                "ut.tincidunt.vehicula@yahoo.edu"
                        ),
                        new Pair<String, String>(
                                "Cullen Gates",
                                "nunc.sed@hotmail.edu"
                        ),
                        new Pair<String, String>(
                                "Quon Cleveland",
                                "vestibulum.ante@protonmail.net"
                        ),
                        new Pair<String, String>(
                                "Gil Pierce",
                                "dolor.fusce@outlook.ca"
                        ),
                        new Pair<String, String>(
                                "Sara O'donnell",
                                "et@icloud.couk"
                        ),
                        new Pair<String, String>(
                                "Valentine Mcdaniel",
                                "blandit@outlook.edu"
                        ),
                        new Pair<String, String>(
                                "Brielle Evans",
                                "sed.facilisis@hotmail.couk"
                        ),
                        new Pair<String, String>(
                                "Chelsea Shaw",
                                "parturient.montes@icloud.net"
                        )
                )
        );


        for(int tmpFrom = 0, tmpTo = 0; tmpTo < buildings.size();){
            tmpTo = random.nextInt(tmpFrom + 1, tmpFrom + 4);
            if(tmpTo >= buildings.size() - 4){
                tmpTo = buildings.size();
            }
            Role role = roles.get(random.nextInt(0, roles.size()));
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);


            var newuser = new User(userData.get(tmpFrom).a, userData.get(tmpFrom).b, encoder.encode("defaultpassword"));

            newuser.setRoles(roleSet);
            var buildinglist = buildings.subList(tmpFrom, tmpTo);

            buildinglist.forEach(b -> b.setUser(newuser));
            newuser.setBuildings(buildinglist);

            users.add(newuser);
            tmpFrom = tmpTo;
        }
        userRepository.saveAll(users);

    }


    public static BigDecimal getRandomBigDecimalInRange(BigDecimal min, BigDecimal max) {
        if (min.compareTo(max) >= 0) {
            throw new IllegalArgumentException("Min value must be less than max value");
        }

        Random random = new Random();

        BigDecimal range = max.subtract(min);
        BigDecimal scaled = range.multiply(new BigDecimal(random.nextDouble()));
        BigDecimal result = scaled.add(min);

        int scale = 2;
        RoundingMode roundingMode = RoundingMode.HALF_UP;

        return result.setScale(scale, roundingMode);
    }

}