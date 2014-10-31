package ca.lc.stimesheet.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.lc.stimesheet.model.PartnerMarketplace;
import ca.lc.stimesheet.model.SubscriptionAccount;
import ca.lc.stimesheet.service.UserSubscriptionService;

@Controller
@RequestMapping("/")
public class PublicController {
    
    @Autowired
    private UserSubscriptionService userSubscriptionService;
    
    @RequestMapping({"/", "/welcome"})
    public String welcome(Model model, @RequestParam(value = "name", required = false) String name) {
        
        // We are showing the Subscriptions and assigned users per Marketplace, so generate a map
        Map<PartnerMarketplace, List<SubscriptionAccount>> subsPerMarketMap = new HashMap<PartnerMarketplace, List<SubscriptionAccount>>();
        model.addAttribute("subsPerMarketMap", subsPerMarketMap);
        
        List<SubscriptionAccount> allSubsAccount = userSubscriptionService.findAllSubscriptionAccounts();
        for (SubscriptionAccount subsAccount : allSubsAccount) {
            List<SubscriptionAccount> marketSubs = subsPerMarketMap.get(subsAccount.getFromMarketplace());
            if (marketSubs == null) {
                marketSubs = new ArrayList<SubscriptionAccount>();
                subsPerMarketMap.put(subsAccount.getFromMarketplace(), marketSubs);
            }
            
            marketSubs.add(subsAccount);
        }

        return "thymeleaf/welcome";
    }
    
    @RequestMapping(value="/login/openid", method=RequestMethod.GET)
    public String login() throws IOException {

        return "thymeleaf/welcome";
    }
    
}
