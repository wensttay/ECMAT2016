package io.github.herocode.ecmat.enums;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public enum RegularExpressions {
    
    EMAIL_PATTERN("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"), 
    PASSWORD_PATTERN("[A-Za-z.,_0-9]+");
    
    String regex;

    RegularExpressions(String expression) {
        
        regex = expression;
    }
    
    public String getRegex(){
        return regex;
    }
    
}
