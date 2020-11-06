<?php
if (is_uploaded_file($_FILES['userfile']['tmp_name'])) {

    echo  $_FILES['userfile']['name'] " \n";
    move_uploaded_file ($_FILES['userfile'] ['tmp_name'], $_FILES['userfile'] ['name']);
} else {
  echo "Possible file upload attack: ";
  echo "filename '". $_FILES['userfile']['tmp_name'] . "'.";
  print_r($_FILES);
}
?>
