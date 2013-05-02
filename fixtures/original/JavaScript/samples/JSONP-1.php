<?php
    header('Content-Type:text/javascript');
    switch($_GET['id']) {
        case '1':
            $result = '{"name":"Justin","age":35}';
            break;
        case '2':
            $result = '{"name":"momor","age":32}';
            break;
        case '3':
            $result = '{"name":"Hamimi","age":3}';
            break;
        default:
            $result = '{"name":"NOBODY","age":0}';
    }
    echo $_GET['jsoncallback'] . '(' . $result . ');';
?>