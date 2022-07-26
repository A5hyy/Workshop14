package vttp2022.workshop14.demo.service;

import vttp2022.workshop14.demo.model.Contact;

public interface ContactsRepo {
    public void save(final Contact ctc);

    public Contact findById(final String contactId);
}