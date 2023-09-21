package com.ticketmaster.controller.db;

import com.ticketmaster.controller.io.AppIO;
import com.ticketmaster.model.Status;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TicketDB {
    private static List<Ticket> list;

    public static void setList(List<Ticket> list){
        if(TicketDB.list == null)
            TicketDB.list = list;
    }

    public static List<Ticket> getList(){
        setList(new ArrayList<>());
        return list;
    }

    public static Ticket findTicketById(int ticketNumber) {
        return list.stream()
                .filter(ticket -> ticket.getId() == ticketNumber)
                .findFirst()
                .orElse(null);
    }

    public static void add(Ticket newTicket) {
        setList(new ArrayList<>());
        list.add(newTicket);
        AppIO.saveS();
    }

    public static List<Ticket> findTicketsByAssignedUser(User user) {
        return list.stream()
                .filter(ticket -> ticket.getUserAssigned().getLogin().equalsIgnoreCase(user.getLogin()))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByAssignedUser(String login) {
        return list.stream()
                .filter(ticket -> ticket.getUserAssigned().getLogin().equalsIgnoreCase(login))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByAssignedTeamName(String name) {
        return list.stream()
                .filter(ticket -> ticket.getTeamAssigned().getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByTicketCreatorLogin(String login) {
        return list.stream()
                .filter(ticket -> ticket.getCreatedBy().getLogin().equalsIgnoreCase(login))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByStatus(String status) {
        return list.stream()
                .filter(ticket -> ticket.getStatus().name().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByStatus(Status status) {
        return list.stream()
                .filter(ticket -> ticket.getStatus() == status)
                .collect(Collectors.toList());
    }
}
