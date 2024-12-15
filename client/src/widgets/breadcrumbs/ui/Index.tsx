import { FC } from "react";
import {useBreadcrumbs} from "@/features";
import {
    Breadcrumb,
    BreadcrumbItem,
    BreadcrumbLink,
    BreadcrumbList,
    BreadcrumbPage,
    BreadcrumbSeparator
} from "@/shared/ui";

export const Index: FC = () => {

    const breadcrumbs = useBreadcrumbs()

    return (
        <Breadcrumb>
            <BreadcrumbList>
                {breadcrumbs.map((breadcrumb, index) => (
                    <BreadcrumbItem key={breadcrumb.path}>
                        {index === breadcrumbs.length - 1 ? (
                            <BreadcrumbPage>
                                {breadcrumb.label}
                            </BreadcrumbPage>
                        ) : (
                            <BreadcrumbLink href={breadcrumb.path}>
                                {breadcrumb.label}
                            </BreadcrumbLink>
                        )}
                        {index !== breadcrumbs.length - 1 && <BreadcrumbSeparator />}
                    </BreadcrumbItem>
                ))}
            </BreadcrumbList>
        </Breadcrumb>
    );
};