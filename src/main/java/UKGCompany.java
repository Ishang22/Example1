import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
//3142->3214
/**
 * Description:<br>
 * Date: 25/01/25-10:51 am
 *
 * @author ishangarg
 * @since
 *
 * https://www.rackspace.com/blog/aws-scaling-best-practices-for-black-friday
 * //The latest version of Spring Boot is 3.4.1
 * //https://stackoverflow.com/questions/56050387/schedule-a-task-in-ec2-auto-scaling-group
 */

// https://www.youtube.com/watch?v=ZJJHm_bd9Zo kafka
// https://www.rackspace.com/blog/aws-scaling-best-practices-for-black-friday
public class UKGCompany {
    public static void main(String[] args) {
        Integer arr1[] = {17, 12, 31, 42, 25, 12, 17};

        List<Integer> ar2 = Arrays.asList(arr1);

        Set<Integer> set = new HashSet<>();

        List<Integer> duplicateElements = ar2.stream().map(a -> {
                    if (set.contains(a)) {
                        return a;
                    }
                    set.add(a);
                    return null;
                }
        ).filter(Objects::nonNull).toList();

        System.out.println(duplicateElements);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<Integer> duplicateElements1 = ar2.stream()
                .filter(a -> !set.add(a)) // `set.add(a)` returns false if the element is already in the set
                .toList(); // Collect the duplicate elements into a list

        System.out.println("Duplicate Elements: " + duplicateElements1);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//      change in one module need to deploy whole project
//      easy debugging and maintance
//      divide large app into small
//      we can use different language
//      different data bases easily scalability
//.     each module manage independently

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////--- SERVICE DISCOVERYYYYYYYY ---////////////////////////////////////////////////////////////////////////////////////////////////////////

// for server discovery these are needed
//artifact - spring-cloud-starter-netflix-eureka-server, groupId - org.springframework.cloud
// at service discovery we have to put annotation - @EnableEurekaServer

// for clients/ or applications that are running if they want to registor they need
// artifact - spring-cloud-starter-netflix-eureka-client, groupId - org.springframework.cloud
//- @EnableEurekaClient
// host and port hardcode prevention

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////how service connect with each other/////////////////////////////////////////////
        /*
                  http web client ->

                1) restTemplate - yeah tu configration me bean dena huga ya @springbootapplication  class ke nich then autowriehuga
                2) feignClient -spring-cloud-starter-openfeign this is @enablefeignclients same as service discovery
         */
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////--- API GATEWAY ---////////////////////////////////////////////////////////////////////////////////////////////////////////
/*

1)  Common Url for all service apne aap identify kre ga konsi service call krni ha
2)  Authentication / Authorization
3)  ratelimiter

name- spring cloud gateway
spring:
    cloud:
        routes:
            id: USER-SERVICE
            uri: lb://USER_SERVICE
            predicates:
            - Path=/users/**,/staffs/*

 */


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////--- Config Server ---///////////////////////////////////////////////////////////////////////////////////
/*
* 1) externalize properties
* 2) can make common for all
* */


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////--- Fault tolerence ---///////////////////////////////////////////////////////////////////////////////////
/*
*
* <artifactId>resilience4j-spring-boot2</artifactId>
*
*
* @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod="ratingHotelFallback")
* @Retry(name="ratingHotelService",fallbackMethod="ratingHotelFallback")
* @RateLimiter(name="",fallback)
* */
/// /////////////////////////////////////////////////////////////JWT TOKEN////////////////////////////////////////////////////
/*
✅ Header → Defines the algorithm & type
✅ Payload → Contains user data (claims)
✅ Signature → Ensures integrity & security

 */
    }

}
