package com.app.restaurant.service;

import java.util.*;

import com.app.restaurant.data.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

@AutoConfiguration
@Service
public class ReservationService {

    private final TableRepository tablesRepository;
    private final ClientRepository clientsRepository;
    private final ReservationRepository reservationsRepository;

    public ReservationService(TableRepository tablesRepository, ClientRepository clientsRepository, ReservationRepository reservationsRepository) {
        this.tablesRepository = tablesRepository;
        this.clientsRepository = clientsRepository;
        this.reservationsRepository = reservationsRepository;
    }

    public List<TableReservation> getTableReservationsForDate(Date date) {
        Iterable<Table> tables = this.tablesRepository.findAll();
        Map<Long, TableReservation> tableReservationMap = new HashMap();
        tables.forEach(table -> {
            TableReservation tableReservation = TableReservation.builder()
                    .tableId(table.getId())
                    .tableName(table.getName())
                    .tableNumber(table.getTableNumber())
                    .clientId(0)
                    .build();
            tableReservationMap.put(table.getId(), tableReservation);
        });

        Iterable<Reservation> reservations = this.reservationsRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            TableReservation reservation1 = tableReservationMap.get(reservation.getTableId());
            reservation1.setDate(date);
            Client client = this.clientsRepository.findById(reservation.getClientId()).get();
            client.setFirstName(client.getFirstName());
            client.setLastName(client.getLastName());
            client.setClientId(client.getClientId());
        });

        List<TableReservation> tablesReservations = new ArrayList<>();
        for (Long id : tableReservationMap.keySet()) {
            tablesReservations.add(tableReservationMap.get(id));
        }

        tablesReservations.sort(new Comparator<TableReservation>() {
            @Override
            public int compare(TableReservation o1, TableReservation o2) {
                if (o1.getTableName().equals(o2.getTableName())) {
                    return o1.getTableNumber().compareTo(o2.getTableNumber());
                }
                return o1.getTableName().compareTo(o2.getTableName());
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

    public List<Table> getTables() {
        Iterable<Table> tables = this.tablesRepository.findAll();
        List<Table> tablesList = new ArrayList<>();
        tables.forEach(room->{tablesList.add(room);});
        tablesList.sort(new Comparator<Table>() {
            @Override
            public int compare(Table o1, Table o2) {
                return o1.getTableNumber().compareTo(o2.getTableNumber());
            }
        });
        return tablesList;
    }
}

