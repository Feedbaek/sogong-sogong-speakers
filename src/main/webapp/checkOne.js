function checkOne(itself, manager) {
    if (manager == "catDog") {
        const man1 = document.getElementsByName("manager1");
        const man4 = document.getElementsByName("manager4");
        const man7 = document.getElementsByName("manager7");
        man1.checked = false;
        man4.checked = false;
        man7.checked = false;
        itself.checked = true;
    }
    else if (manager == "repFish") {
        const man2 = document.getElementsByName("manager2");
        const man5 = document.getElementsByName("manager5");
        const man8 = document.getElementsByName("manager8");
        man2.checked = false;
        man5.checked = false;
        man8.checked = false;
        itself.checked = true;
    }
    else if (manager == "bird") {
        const man3 = document.getElementsByName("manager3");
        const man6 = document.getElementsByName("manager6");
        const man9 = document.getElementsByName("manager9");
        man3.checked = false;
        man6.checked = false;
        man9.checked = false;
        itself.checked = true;
    }
}