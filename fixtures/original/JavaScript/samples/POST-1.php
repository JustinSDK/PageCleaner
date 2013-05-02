<?php
    header('Content-Type:text/html;charset=UTF-8');
    switch($_POST['url']) {
        case 'http://caterpillar.onlyfun.net':
            $result = 'urlExisted';
            break;
        case 'http://openhome.cc':
            $result = 'urlExisted';
            break;
        default:
            $result = '';
    }
    echo $result;
?>