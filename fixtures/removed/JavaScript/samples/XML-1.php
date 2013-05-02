<?php
    header('Content-Type:text/xml;charset=UTF-8');
    echo '<?xml version="1.0" encoding="utf-8"?>';
    echo '<select>';
    switch($_GET['category']) {
        case 'theory':
            $result = '<option value="algorithm">Algorhtim</option>' .
                      '<option value="graphic">Computer Graphics</option>' .
                      '<option value="pattern">Design Pattern</option>';
            break;
        case 'language':
            $result = '<option value="c">C</option>' .
                      '<option value="cpp">C++</option>' .
                      '<option value="java">Java</option>' . 
                      '<option value="python">Python</option>' . 
                      '<option value="javascript">JavaScript</option>';
            break;
        case 'web':
            $result = '<option value="servlet">Servlet</option>' .
                      '<option value="jsp">JSP</option>' .
                      '<option value="struts">Struts</option>' . 
                      '<option value="springmvc">Spring MVC</option>';
            break;
        default:
            $result = '<option>-- No items --</option>';
    }
    echo $result;
    echo '</select>';
?>