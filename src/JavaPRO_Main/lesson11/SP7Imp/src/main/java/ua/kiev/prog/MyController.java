package ua.kiev.prog;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.kiev.prog.utils.GroupIdWithListOfContacts;

import java.util.List;

@Controller
public class MyController {
    static final int DEFAULT_GROUP_ID = -1;
    static final int ITEMS_PER_PAGE = 6;

    private final ContactService contactService;
    private final GroupService groupService;

    public MyController(ContactService contactService, GroupService groupService) {
        this.contactService = contactService;
        this.groupService = groupService;
    }

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        List<Contact> contacts = contactService
                .findAll(PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("groups", contactService.findGroups());
        model.addAttribute("contacts", contacts);
        model.addAttribute("allPages", getPageCount());

        return "index";
    }

    @GetMapping("/reset")
    public String reset() {
        contactService.reset();
        return "redirect:/";
    }

    @GetMapping("/contact_add_page")
    public String contactAddPage(Model model) {
        model.addAttribute("groups", contactService.findGroups());
        return "contact_add_page";
    }

    @GetMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @GetMapping("/group/{id}")
    public String listGroup(
            @PathVariable(value = "id") long groupId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;
        if (page < 0) page = 0;

        List<Contact> contacts = contactService
                .findByGroup(group, PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("groups", contactService.findGroups());
        model.addAttribute("contacts", contacts);
        model.addAttribute("byGroupPages", getPageCount(group));
        model.addAttribute("groupId", groupId);

        return "index";
    }


    private String pattern = null;

    @PostMapping(value = "/search")
    public String postSearch(@RequestParam String pattern,
                             @RequestParam(required = false, defaultValue = "0") Integer page,
                             Model model) {
        if (page < 0) page = 0;
        this.pattern = pattern;
        model.addAttribute("groups", contactService.findGroups());
        model.addAttribute("contacts", contactService.findByPattern(
                pattern, PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id")));
        model.addAttribute("searchPages", getPageCountSearch(pattern));

        return "index";
    }

    @GetMapping(value = "/search")
    public String getSearch(@RequestParam(required = false, defaultValue = "0") Integer page,
                            Model model) {
        if (page < 0) page = 0;
        model.addAttribute("groups", contactService.findGroups());
        model.addAttribute("contacts", contactService.findByPattern(
                pattern, PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id")));
        model.addAttribute("searchPages", getPageCountSearch(pattern));

        return "index";
    }


    @PostMapping(value = "/contact/delete")
    public ResponseEntity<Void> delete(
            @RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            contactService.deleteContacts(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "deleteGroup/{id}")
    public String deleteGroup(@PathVariable(value = "id") long groupId) {
        groupService.deleteById(groupId);
        return "redirect:/";
    }


    @PostMapping(value = "/contact/switchGroup")
    public ResponseEntity<Void> deleteContacts(@RequestBody GroupIdWithListOfContacts groupIdWithListOfContacts) {

        return contactService.switchGroupFromListContacts(
              groupIdWithListOfContacts.getGroupId(), groupIdWithListOfContacts.getContacts());
    }


    @PostMapping(value = "/contact/add")
    public String contactAdd(@RequestParam(value = "group") long groupId,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String phone,
                             @RequestParam String email) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;

        Contact contact = new Contact(group, name, surname, phone, email);
        contactService.addContact(contact);

        return "redirect:/";
    }

    @PostMapping(value = "/group/add")
    public String groupAdd(@RequestParam String name) {
        contactService.addGroup(new Group(name));
        return "redirect:/";
    }

    @PostMapping("/uploadContacts")
    public String uploadContacts(@RequestParam("file") MultipartFile multipartFile) {
        contactService.createContactsObjFromFile(multipartFile);
        return "redirect:/";
    }


    private long getPageCount() {
        long totalCount = contactService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCount(Group group) {
        long totalCount = contactService.countByGroup(group);
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCountSearch(String pattern) {
        long totalCount = contactService.countByPattern(pattern);
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
