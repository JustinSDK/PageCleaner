<?php
    header('Content-Type:text/html;charset=UTF-8');
?>    
<select>
<?php
    switch($_GET['category']) {
        case 'theory':
            $result = '<option value="algorithm">Algorithm</option>' .
                      '<option value="graphic">Computer Graphics</option>' .
                      '<option value="pattern">Design Patterm</option>';
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
?>
</select>