import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * A BusSg class encapsulate the data related to the bus services and
 * bus stops in Singapore, and supports queries to the data.
 *
 * @author: Ooi Wei Tsang (Modified by: Ng Jia Yuan Group 08L)
 * @version: CS2030S AY20/21 Semester 2, Lab 8
 */
class BusSg {

  /**
   * Given a bus stop and a name, find the bus services that serve between
   * the given stop and any bus stop with matching mame.
   * @param  stop The bus stop.  Assume to be not null.
   * @param  searchString The (partial) name of other bus stops, assume not null.
   * @return The (optional) bus routes between the stops.
   */
  public static CompletableFuture<BusRoutes> findBusServicesBetween(BusStop stop, 
      String searchString) {
    try {
      return stop.getBusServices().thenApply(x -> x
          .stream()
          .collect(Collectors.toMap(
              service -> service,
              service -> service.findStopsWith(searchString))))
        .thenApply(x -> new BusRoutes(stop, searchString, x));
      /*
      Map<BusService, Set<BusStop>> validServices = stop.getBusServices().stream()
          .collect(Collectors.toMap(
              service -> service, 
              service -> service.findStopsWith(searchString)));
      */
    } catch (CompletionException e) {
      System.err.println("Unable to complete query: " + e);
      return CompletableFuture.<BusRoutes>supplyAsync(
          () -> new BusRoutes(stop, searchString, Map.of()));
    }
  }
}
