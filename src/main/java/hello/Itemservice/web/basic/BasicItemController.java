package hello.Itemservice.web.basic;

import hello.Itemservice.domain.item.Item;
import hello.Itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemid}")
    public String item(@PathVariable long itemid, Model model) {
        Item item = itemRepository.findById(itemid);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {

        itemRepository.save(item);

        // model.addAttribute("item", item); // 자동 추가, 생략 가능
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        //("item")생략시 클래스의 첫번째 글자를 소문자로 바꿔 모델에 넣음
        itemRepository.save(item);

        // model.addAttribute("item", item); // 자동 추가, 생략 가능
        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item) {
        //("item")생략시 클래스의 첫번째 글자를 소문자로 바꿔 모델에 넣음
        itemRepository.save(item);

        // model.addAttribute("item", item); // 자동 추가, 생략 가능
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    //테스트용 데이터 추가

    @PostConstruct
    public void init() {

        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
