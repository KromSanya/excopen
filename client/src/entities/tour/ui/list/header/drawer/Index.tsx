import {FC} from 'react';
import {Drawer, DrawerClose, DrawerContent, DrawerFooter, DrawerHeader, DrawerTitle, DrawerTrigger} from "@/shared/ui";
import {Filter} from "lucide-react";
import {Orientation} from "@/shared/types";
import {Form} from "@/features";

export const Index: FC = () => {
    return (
        <div className={"lg:hidden"}>
            <Drawer>
                <DrawerTrigger>
                    <Filter className={"w-4 h-4 hover:text-gray-400 transition-all duration-300"}/>
                </DrawerTrigger>
                <DrawerContent>
                    <DrawerHeader>
                        <DrawerClose className={"flex justify-center w-full"}>
                            <div className="w-16 h-1.5 bg-gray-300 rounded-full mx-auto cursor-pointer"></div>
                        </DrawerClose>
                    </DrawerHeader>
                    <DrawerFooter>
                        <DrawerTitle>Куда теперь?</DrawerTitle>
                        <Form orientation={Orientation.HORIZONTAL}/>
                    </DrawerFooter>
                </DrawerContent>
            </Drawer>
        </div>
    );
};