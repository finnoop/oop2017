package bank;

import bank.accounts.Account;

import java.util.List;

public class Bank {
    private String name;
    private List<Contact> contacts;
    private List<Customer> customers;
    private List<Account> accounts;

    public Bank(String name, Contact contact) {
        this.name = name;
        this.contacts.add(contact);
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        if (!name.equals(bank.name)) return false;
        if (!contacts.equals(bank.contacts)) return false;
        if (customers != null ? !customers.equals(bank.customers) : bank.customers != null) return false;
        return accounts != null ? accounts.equals(bank.accounts) : bank.accounts == null;

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
