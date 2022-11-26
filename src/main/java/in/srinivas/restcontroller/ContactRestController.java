package in.srinivas.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.srinivas.constants.AppConstants;
import in.srinivas.entity.Contact;
import in.srinivas.props.AppProperties;
import in.srinivas.service.ContactServiceImpl;

@RestController
public class ContactRestController {
	
	@Autowired
	private AppProperties aprop;
	
	@Autowired
	private ContactServiceImpl service;
	
	@PostMapping(value="/saveContact")
	public String saveContact(@RequestBody Contact contact) {
		boolean savedContact = service.saveContact(contact);
		Map<String, String> messages = aprop.getMessages();
		if(savedContact) {
			return messages.get(AppConstants.CONTACT_SAVE_SUCCESS);
		}	else {	
			return messages.get(AppConstants.CONTACT_SAVE_FAIL);
				}
		}
		
	@GetMapping(value="/getContacts")
	public List<Contact> getAllContacts(){
		 List<Contact> allContacts = service.getAllContacts();
		 return allContacts;
	}
	@GetMapping(value="/contactEdit/{cid}")
	public Contact getContactById(@PathVariable("cid") Integer contactId) {
		Contact contactById = service.getContactById(contactId);
		return contactById;
	}
	@DeleteMapping(value="/delet/{cid}")
	public String deleteContactById(@PathVariable("cid") Integer contactId) {
		boolean deletedContactById = service.deleteContactById(contactId);
		Map<String, String> messages = aprop.getMessages();
		if(deletedContactById) {
			return messages.get(AppConstants.CONTACT_DELETE_SUCCESS);
		}else {
			return messages.get(AppConstants.CONTACT_DELETE_FAIL);
		}
	}
	
		
	

}
