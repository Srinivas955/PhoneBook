package in.srinivas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.srinivas.entity.Contact;
import in.srinivas.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private ContactRepository crepo;

	@Override
	public boolean saveContact(Contact contact) {
		contact.setActiveSw("Y");
		Contact save = crepo.save(contact);
	if(save.getContactId() != null) {
		return true;
	}
		return false; 
	}

	@Override
	public List<Contact> getAllContacts() {
		Contact contact = new Contact();
		contact.setActiveSw("Y");
		List<Contact> findAll = crepo.findAll(Example.of(contact));
		return findAll;
	
		
	}

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> findById = crepo.findById(contactId);
		if(findById.isPresent()) {
			return findById.get();
		}		
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		Optional<Contact> findById = crepo.findById(contactId);
		if(findById.isPresent()) {
			Contact contact = findById.get();
			contact.setActiveSw("N");
			crepo.save(contact);
			return true;
		}
		return false;
	}

}
