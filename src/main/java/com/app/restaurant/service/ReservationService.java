package com.app.restaurant.service;

import com.app.restaurant.data.Client;
import com.app.restaurant.data.Reservation;
import com.app.restaurant.data.RestaurantTable;
import com.app.restaurant.repository.ClientRepository;
import com.app.restaurant.repository.ReservationRepository;
import com.app.restaurant.repository.RestaurantTableRepository;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.*;

@AutoConfiguration
@Service
public class ReservationService {

    private final RestaurantTableRepository tablesRepository;
    private final ClientRepository clientsRepository;
    private final ReservationRepository reservationsRepository;

    public ReservationService(RestaurantTableRepository tablesRepository, ClientRepository clientsRepository, ReservationRepository reservationsRepository) {
        this.tablesRepository = tablesRepository;
        this.clientsRepository = clientsRepository;
        this.reservationsRepository = reservationsRepository;
    }

    public List<Reservation> getTableReservationsForDate(Date date) {
        Random random = new Random();
        long randomLong = random.nextLong();

        List<Reservation> tables = this.reservationsRepository.findAll();
        Map<Long, Reservation> tableReservationMap = new HashMap();
        tables.forEach(table -> {
            Reservation newReservation = new Reservation();
            newReservation.setTableId(table.getTableId());
            newReservation.setReservationDate(date);
            newReservation.setReservationId(randomLong);
            tableReservationMap.put(table.getTableId(), newReservation);
        });

        Iterable<Reservation> reservations = this.reservationsRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            Reservation reservation1 = tableReservationMap.get(reservation.getTableId());
            reservation1.setReservationDate(date);
            Client client = this.clientsRepository.findById(reservation.getClientId()).get();
            client.setFirstName(client.getFirstName());
            client.setLastName(client.getLastName());
            client.setClientId(client.getClientId());
        });

        List<Reservation> tablesReservations = new ArrayList<>();
        for (Long id : tableReservationMap.keySet()) {
            tablesReservations.add(tableReservationMap.get(id));
        }

        tablesReservations.sort(new Comparator<Reservation>() {
            @Override
            public int compare(Reservation o1, Reservation o2) {
                if (o1.getReservationDate().equals(o2.getReservationDate())) {
                    return o1.getReservationDate().compareTo(o2.getReservationDate());
                }
                return o1.getReservationName().compareTo(o2.getReservationName());
            }
        });

        return tablesReservations;
    }

    public List<Client> getRestaurantClients(){
        Iterable<Client> clients = this.clientsRepository.findAll();
        List<Client> clientsList = new ArrayList<>();
        clients.forEach(guest->{clientsList.add(guest);});
        clientsList.sort(new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                if (o1.getLastName().equals(o2.getLastName())){
                    return o1.getFirstName().compareTo(o2.getFirstName());
                }
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return clientsList;
    }

    public void addClients(Client client) {
        if (null == client){
            throw new RuntimeException("Client cannot be null");
        }
        this.clientsRepository.save(client);
    }

    public List<RestaurantTable> getTables() {
        Iterable<RestaurantTable> tables = this.tablesRepository.findAll();
        List<RestaurantTable> tablesList = new ArrayList<>();
        tables.forEach(room->{tablesList.add(room);});
        tablesList.sort(new Comparator<RestaurantTable>() {
            @Override
            public int compare(RestaurantTable o1, RestaurantTable o2) {
                return o1.getTableNumber().compareTo(o2.getTableNumber());
            }
        });
        return tablesList;
    }
}

